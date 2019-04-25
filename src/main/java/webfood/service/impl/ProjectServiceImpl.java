package webfood.service.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import webfood.mapper.ProjectMapper;
import webfood.mapper.ScatterProjectMapper;
import webfood.model.Project;
import webfood.model.ScatterProject;
import webfood.service.CookieService;
import webfood.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private ScatterProjectMapper scatterProjectMapper;

	@Override
	public void deleteById(Integer id) {
		projectMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteSById(Integer id) {
		scatterProjectMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Project project) {
		projectMapper.insertSelective(project);

	}

	@Override
	public List<Project> queryAll() {

		return projectMapper.queryAll();
	}

	@Override
	public Project queryById(Integer id) {

		return projectMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Project project) {
		projectMapper.updateByPrimaryKeySelective(project);
	}

	@Override
	public List<ScatterProject> queryAllSp() {
		// TODO Auto-generated method stub
		return scatterProjectMapper.queryAll();
	}

	@Override
	public void updateSp(ScatterProject project) {

		scatterProjectMapper.updateByPrimaryKeySelective(project);
	}

	@Override
	public void insertSp(ScatterProject project) {

		scatterProjectMapper.insertSelective(project);
	}

	@Override
	public ScatterProject querySprojectById(int id) {

		return scatterProjectMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Project> query(StringBuffer sqlWhere) {

		return projectMapper.query(sqlWhere.toString());
	}

	@Override
	public List<ScatterProject> querySp(StringBuffer sqlWhere) {

		return scatterProjectMapper.query(sqlWhere);
	}

	@Override
	public String insertPic(HttpServletRequest request) {

		ServletContext sc = request.getSession().getServletContext();
		String dir = sc.getRealPath("/upload"); // 设定文件保存的目录
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();

				while (iter.hasNext()) {
					String picnext = iter.next().toString();
					if (StringUtils.isNotBlank(picnext)) {
						String suffix = "";
						// 一次遍历所有文件
						MultipartFile file = multiRequest.getFile(picnext);
						if (file != null) {
							String originalFilename = file.getOriginalFilename();
							if (originalFilename != null && !"".equals(originalFilename)) {
								int index = originalFilename.indexOf(".");
								if (index > 0) {
									suffix = originalFilename.substring(index, originalFilename.length());
								}
							}
							if (!".jpg.png.gif".contains(suffix)) {
								return null;
							}
							return CookieService.upload(request, file);
						}
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
