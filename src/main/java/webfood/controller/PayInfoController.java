package webfood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webfood.config.CONST;
import webfood.mapper.PayInfoMapper;
import webfood.mapper.ProcessMapper;
import webfood.mapper.ProjectBuildMapper;
import webfood.model.PayInfo;
import webfood.model.Process;
import webfood.model.Project;
import webfood.model.ProjectBuild;
import webfood.service.PicService;
import webfood.service.ProjectService;
import webfood.utils.DateTimeUtil;

@Controller
public class PayInfoController extends BaseController {

	private final static Logger log = Logger.getLogger(ProjectController.class);

	@Autowired
	PayInfoMapper payInfoMapper;

	@Autowired
	ProjectService projectService;

	@Autowired
	ProcessMapper processMapper;

	@Autowired
	ProjectBuildMapper projectBuildMapper;

	@Autowired
	PicService picService;

	@RequestMapping("/payinfo_add")
	public ModelAndView payinfoAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/payinfo_add");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			ProjectBuild pb = projectBuildMapper.selectByPrimaryKey(Integer.parseInt(request.getParameter("bid")));
			if (StringUtils.isEmpty(pb.getPayWay())) {
				mv.setViewName("redirect:/update_payinfo?id=" + id + "&msg=1");
				return mv;
			}
			mv.addObject("pb", pb);
			if ("1".equals(pb.getPayWay())) {
				mv.setViewName("/project/payinfo_once_add");
				List<PayInfo> payList = payInfoMapper.queryByBid(pb.getId());
				if (payList.size() > 0) {
					mv.setViewName("/project/payinfo_once_modify");
					mv.addObject("payInfo", payList.get(0));
				}
			} else {
				List<Process> list = processMapper.queryByBidNoPay(pb.getId());
				if (list.size() == 0) {
					mv.setViewName("redirect:/update_payinfo?id=" + id + "&msg=2");
					return mv;
				}
				mv.addObject("list", list);
			}

		}
		mv.addObject("id", id);
		mv.addObject("bid", request.getParameter("bid"));

		return mv;
	}

	@RequestMapping("/payinfo_update")
	public ModelAndView payinfo_update(HttpServletRequest request, Integer pid) {
		ModelAndView mv = new ModelAndView("/project/payinfo_modify");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
		}
		PayInfo info = payInfoMapper.selectByPrimaryKey(pid);
		mv.addObject("info", info);

		return mv;
	}

	@RequestMapping("/payinfo_update1")
	public ModelAndView payinfo_update1(HttpServletRequest request, PayInfo info,
			@RequestParam("files") MultipartFile[] files) {
		ModelAndView mv = new ModelAndView("redirect:/update_payinfo?id=" + info.getProjectId());
		Project project = projectService.queryById(info.getId());
		mv.addObject("project", project);
		if (files.length > 0) {
			picService.insertMultiPic(files, request, info.getId(), CONST.PIC_pays);
		}

		payInfoMapper.updateByPrimaryKeySelective(info);

		return mv;
	}

	@RequestMapping("/payInfo_add1")
	public ModelAndView builedAdd1(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {

		ModelAndView mv = new ModelAndView("redirect:/update_payinfo?id=" + request.getParameter("projectId"));
		String pbId = request.getParameter("processBuildId");
		String pid = request.getParameter("projectId");
		if (StringUtils.isEmpty(pbId) || !StringUtils.isNumeric(pbId) || StringUtils.isEmpty(pid)
				|| !StringUtils.isNumeric(pid)) {
			log.error("pbId valid!!!!!!!!!");
			return null;
		}
		Project project = projectService.queryById(Integer.parseInt(request.getParameter("projectId")));
		PayInfo pf = new PayInfo();
		String pindex = request.getParameter("pindex");
		String payedAmount = request.getParameter("payedAmount");
		String payWay = request.getParameter("payWay");
		String payTime = request.getParameter("payTime");
		String processs = request.getParameter("process_id");

		String[] ps = processs.split("_");
		String processId = ps[0];
		String process = ps[1];
		// String auditAmount = request.getParameter("auditAmount");
		pf.setPindex(pindex);
		if (StringUtils.isNotBlank(payedAmount) && StringUtils.isNumeric(payedAmount)) {
			pf.setPayedAmount(Double.parseDouble(payedAmount));
		}
		pf.setPayTime(payTime);
		pf.setPayWay(payWay);
		String[] base64str_payPics = request.getParameterValues("base64str_payPics");
		if (isPicNotEmpty(files, base64str_payPics)) {
			picService.deletePic(pf.getId(), CONST.PIC_yssqPics);

		}
		if (files != null && files.length > 0) {
			picService.insertMultiPic(files, request, project.getId(), CONST.PIC_pays);
		}
		if (isGaopaiyiNotEmpty(base64str_payPics)) {
			picService.insertMultiBase64Pic(request, base64str_payPics, pf.getId(), CONST.PIC_pays);
		}
		pf.setProcess(Double.parseDouble(process));
		pf.setProcessId(Integer.parseInt(processId));
		pf.setProcessBuildId(Integer.parseInt(pbId));
		pf.setCreateTime(DateTimeUtil.getDateTimeNow());
		pf.setProjectId(Integer.parseInt(pid));
		// if (StringUtils.isNotBlank(auditAmount))
		// pf.setAuditAmount(Double.parseDouble(auditAmount));
		payInfoMapper.insertSelective(pf);
		// if (project.getAuditAmount() != null) {
		// project.setAuditAmount(project.getAuditAmount() +
		// Double.parseDouble(auditAmount));
		//
		// }
		projectService.update(project);

		return mv;
	}

	@RequestMapping("/payInfo_once_add1")
	public ModelAndView payInfoOnceAdd1(HttpServletRequest request, @RequestParam("files") MultipartFile[] files)
			throws Exception {

		String pindex = request.getParameter("pindex");
		String process = request.getParameter("process");
		String payedAmount = request.getParameter("payedAmount");
		String payWay = request.getParameter("payWay");
		String payTime = request.getParameter("payTime");
		String pbId = request.getParameter("processBuildId");
		String pid = request.getParameter("projectId");
		if (StringUtils.isEmpty(pbId) || !StringUtils.isNumeric(pbId) || StringUtils.isEmpty(pid)
				|| !StringUtils.isNumeric(pid)) {
			throw new Exception("pbId valid!!!!!!!!!");
		}
		PayInfo pf = new PayInfo();
		pf.setPindex(pindex);
		if (StringUtils.isNotBlank(payedAmount) && StringUtils.isNumeric(payedAmount)) {
			pf.setPayedAmount(Double.parseDouble(payedAmount));
		}
		pf.setProcess(Double.parseDouble(process));
		pf.setPayTime(payTime);
		pf.setPayWay(payWay);
		pf.setProcessBuildId(Integer.parseInt(pbId));
		pf.setProjectId(Integer.parseInt(pid));
		ModelAndView mv = new ModelAndView("redirect:/update_payinfo?id=" + request.getParameter("projectId"));

		String[] base64str_payPics = request.getParameterValues("base64str_payPics");

		pf.setCreateTime(DateTimeUtil.getDateTimeNow());
		payInfoMapper.insertSelective(pf);

		if (isPicNotEmpty(files, base64str_payPics)) {
			picService.deletePic(pf.getId(), CONST.PIC_pays);

		}

		if (isAttachNotEmpty(files)) {
			picService.insertMultiPic(files, request, pf.getId(), CONST.PIC_pays);
		}
		if (isGaopaiyiNotEmpty(base64str_payPics)) {
			picService.insertMultiBase64Pic(request, base64str_payPics, pf.getId(), CONST.PIC_pays);
		}

		return mv;
	}

	@RequestMapping("/payInfo_once_modify1")
	public ModelAndView payInfoOnceModify1(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {

		String pindex = request.getParameter("pindex");
		String process = request.getParameter("process");
		String payedAmount = request.getParameter("payedAmount");
		String payWay = request.getParameter("payWay");
		String payTime = request.getParameter("payTime");
		String pbId = request.getParameter("processBuildId");
		String pid = request.getParameter("projectId");
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(pbId) || !StringUtils.isNumeric(pbId) || StringUtils.isEmpty(pid)
				|| !StringUtils.isNumeric(pid)) {
			log.error("pbId valid!!!!!!!!!");
			return null;
		}
		PayInfo pf = new PayInfo();
		pf.setId(Integer.parseInt(id));
		pf.setPindex(pindex);
		if (StringUtils.isNotBlank(payedAmount)) {
			pf.setPayedAmount(Double.parseDouble(payedAmount));
		}
		pf.setProcess(Double.parseDouble(process));
		pf.setPayTime(payTime);
		pf.setPayWay(payWay);
		pf.setProcessBuildId(Integer.parseInt(pbId));
		pf.setProjectId(Integer.parseInt(pid));
		ModelAndView mv = new ModelAndView("redirect:/update_payinfo?id=" + request.getParameter("projectId"));

		String[] base64str_payPics = request.getParameterValues("base64str_payPics");
		if (isPicNotEmpty(files, base64str_payPics)) {
			picService.deletePic(pf.getId(), CONST.PIC_pays);

		}

		if (isAttachNotEmpty(files)) {
			picService.insertMultiPic(files, request, pf.getId(), CONST.PIC_pays);
		}
		if (isGaopaiyiNotEmpty(base64str_payPics)) {
			picService.insertMultiBase64Pic(request, base64str_payPics, pf.getId(), CONST.PIC_pays);
		}
		pf.setCreateTime(DateTimeUtil.getDateTimeNow());
		payInfoMapper.updateByPrimaryKeySelective(pf);

		return mv;
	}

	@RequestMapping("/audit")
	public ModelAndView audit(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/project/audit");
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			Project project = projectService.queryById(Integer.parseInt(id));
			mv.addObject("project", project);
			ProjectBuild pb = projectBuildMapper.selectByPrimaryKey(Integer.parseInt(request.getParameter("bid")));
			if (StringUtils.isEmpty(pb.getPayWay())) {
				mv.setViewName("redirect:/update_payinfo?id=" + id + "&msg=未选择支付方式，不能录入审计信息！");
				return mv;
			}
			mv.addObject("pb", pb);

		}
		mv.addObject("id", id);
		mv.addObject("bid", request.getParameter("bid"));

		return mv;
	}

}
