package com.emac.gipsi.shotgun.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emac.gipsi.shotgun.dto.FamilleDto;
import com.emac.gipsi.shotgun.dto.FamilleShotgunDto;
import com.emac.gipsi.shotgun.dto.ResidenceDto;
import com.emac.gipsi.shotgun.dto.ResidenceShotgunDto;
import com.emac.gipsi.shotgun.dto.ShotgunDto;
import com.emac.gipsi.shotgun.model.Famille;
import com.emac.gipsi.shotgun.model.PartieCommune;
import com.emac.gipsi.shotgun.model.Residence;
import com.emac.gipsi.shotgun.model.Shotgun;
import com.emac.gipsi.shotgun.repositories.PartieCommuneRepository;
import com.emac.gipsi.shotgun.repositories.ResidenceRepository;
import com.emac.gipsi.shotgun.repositories.ShotgunRepository;
import com.emac.gipsi.shotgun.services.IResidenceService;

@Service("residenceService")
@Transactional
public class ResidenceService implements IResidenceService {

	@Autowired
	private ResidenceRepository residenceRepository;
	
	@Autowired
	private PartieCommuneRepository partieccommuneRepository;
	
	@Autowired
	private ShotgunRepository shotgunRepository;
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	public ResidenceService(ResidenceRepository residenceRepository, ShotgunRepository shotgunRepository, PartieCommuneRepository partieCommuneRepository) {
		this.residenceRepository = residenceRepository;
		this.shotgunRepository = shotgunRepository;
		this.partieccommuneRepository = partieCommuneRepository;
	}
	
	@Override
	public List<ResidenceShotgunDto> getResidences() {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<List<ResidenceShotgunDto>>() {}.getType();
		List<ResidenceShotgunDto> result = modelMapper.map(residenceRepository.findAll(), listType);
		
		return result;
	}
	
	@Override
	public ResidenceShotgunDto getResidence(int id) {
		Optional<Residence> residence = residenceRepository.findById(id);
		if (residence.isPresent()) {
			ResidenceShotgunDto result = new ResidenceShotgunDto();
			modelMapper.map(residence.get(), result);
			return result;
		}

		return null;
	}

	@Override
	public Map<String, List<ShotgunDto>> getResidencesWithShotguns() {
		Map<String, List<ShotgunDto>> result = new HashMap<String, List<ShotgunDto>>();
		List<ResidenceShotgunDto> residenceShotgunDtos = this.getResidences();
		
		for(ResidenceShotgunDto r: residenceShotgunDtos) {
			result.put(r.getName(), new ArrayList<ShotgunDto>());
		}
		
		List<Shotgun> listshotgun = this.shotgunRepository.findAll();
		List<PartieCommune> listpc = this.partieccommuneRepository.findAll();
		
		//result.get("Jarlard 1").add(arg0)
		
		for(Shotgun shotgun: listshotgun) {
			for(PartieCommune pc: listpc) {
				if (shotgun.getShotgunPlace().getId() == pc.getId()) {
					
					ShotgunDto shotgunToAdd = new ShotgunDto();
					modelMapper.map(shotgun ,shotgunToAdd);
									
					result.get(pc.getLocalisation().getName()).add(shotgunToAdd);
					
					break;
				}
			}
			
			
		}
		
 		
		return result;
	}

}


