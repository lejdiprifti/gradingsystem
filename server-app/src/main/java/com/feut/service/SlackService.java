package com.feut.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.feut.converter.SlackConverter;
import com.feut.entity.SlackEntity;
import com.feut.model.SlackModel;
import com.feut.repository.SlackRepository;
import com.feut.repository.StudentRepository;

@Service
public class SlackService {
	
	@Autowired
	private SlackRepository slackRepository;
	
	@Autowired
	private SlackConverter slackConverter;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public SlackService() {
		
	}
	
	public List<SlackModel> getByStudent(Long studentId){
		try {
		return slackConverter.toModel(slackRepository.getByStudent(studentId));
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slack record not found.");
		}
	}
	
	public void save(SlackModel model) {
		try {
			SlackEntity entity = slackRepository.getByChannelName(model.getChannelName());
			entity.setUrl(model.getUrl());
			slackRepository.edit(entity);
		} catch (NoResultException e) {
		SlackEntity entity = new SlackEntity();
		entity.setChannelName(model.getChannelName());
		entity.setChannelId(model.getChannelId());
		entity.setStudent(studentRepository.getById(model.getStudentId()));
		entity.setUrl(model.getUrl());
		entity.setBotToken(model.getBotToken());
		entity.setActive(true);
		slackRepository.save(entity);
	}
	}
	
	public void delete(Long id) {
		try {
			SlackEntity entity = slackRepository.getAllById(id);
			entity.setActive(false);
			slackRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slack record not found.");
		}
	}
	
	public void activate(Long id) {
		try {
			SlackEntity entity = slackRepository.getAllById(id);
			entity.setActive(true);
			slackRepository.edit(entity);
		} catch (NoResultException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slack record not found.");
		}
	}
}
