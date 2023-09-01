package br.edu.ifsp.arq.dw2s6.exam.domain.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "task")
public class Task {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @NotNull
	 private String description;
	 @NotNull
	 private Date task_date;
	 @NotNull
	 private String observation; 
	 //Referencia Usuário
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private User user;
	 //Referencia Tag
	 @ManyToOne
	 @JoinColumn(name = "tag_id")
	 private Tag tag;
	 public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getTask_date() {
			return task_date;
		}
		public void setTask_date(Date task_date) {
			this.task_date = task_date;
		}
		public String getObservation() {
			return observation;
		}
		public void setObservation(String observation) {
			this.observation = observation;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Tag getTag() {
			return tag;
		}
		public void setTag(Tag tag) {
			this.tag = tag;
		}
}
