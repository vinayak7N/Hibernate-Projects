package com.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SkillSet {

	@Column(name = "skill_name", length = 20, nullable = false, unique = true)
	private String skillName;

	@Column(name = "skill_rating", nullable = false)
	private int skillRating;

	public SkillSet() {
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getSkillRating() {
		return skillRating;
	}

	public void setSkillRating(int skillRating) {
		this.skillRating = skillRating;
	}

	@Override
	public String toString() {
		return "SkillSet [skillName=" + skillName + ", skillRating=" + skillRating + "]";
	}
}
