package com.todo.todoappmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.todo.todoappmanagement.entity.ToboEntity;

import jakarta.transaction.Transactional;


@Repository
public interface TodoDao extends JpaRepository<ToboEntity, Long> {

	@Query("select b from ToboEntity b where b.id=:id")
	public List<ToboEntity> getTaskDetaisById(Long id);

	@Modifying
	@Transactional
	@Query("update ToboEntity set isTaskcomplted=:istaskComplted where id=:id ")
	public void updateDetaildById(Long id, String istaskComplted);

}
