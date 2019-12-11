package com.emac.gipsi.shotgun.services;

import java.util.List;
import java.util.Map;

import com.emac.gipsi.shotgun.dto.ResidenceDto;
import com.emac.gipsi.shotgun.dto.ResidenceShotgunDto;
import com.emac.gipsi.shotgun.dto.ShotgunDto;
import com.emac.gipsi.shotgun.model.Residence;

public interface IResidenceService {
	public List<ResidenceShotgunDto> getResidences();
	
	public ResidenceShotgunDto getResidence(int id);
	
	public  Map<String, List<ShotgunDto>> getResidencesWithShotguns();
}
