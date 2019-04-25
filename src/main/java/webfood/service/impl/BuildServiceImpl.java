package webfood.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webfood.mapper.PayInfoMapper;
import webfood.mapper.ProcessMapper;
import webfood.mapper.ProjectBuildMapper;
import webfood.model.PayInfo;
import webfood.model.Process;
import webfood.model.ProjectBuild;
import webfood.service.BuildService;

@Service("buildService")
public class BuildServiceImpl implements BuildService
{

	@Autowired
	ProjectBuildMapper projectBuildMapper;

	@Autowired
	PayInfoMapper payInfoMapper;

	@Autowired
	ProcessMapper processMapper;

	@Override
	public List<ProjectBuild> queryByPid(int pid)
	{

		List<ProjectBuild> list = projectBuildMapper.queryByPid(pid);
		for (ProjectBuild build : list)
		{
			List<PayInfo> plist = payInfoMapper.queryByBid(build.getId());
			if (plist.size() > 0)
			{
				build.setPlist(plist);
			}
			List<Process> pclist = processMapper.queryByBid(build.getId());
			if (pclist.size() > 0)
			{
				build.setPclist(pclist);
			}
		}

		return list;
	}

}
