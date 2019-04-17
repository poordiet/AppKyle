package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Skill {
    private int skillId;
    private String skill;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "skill", nullable = false, length = 150)
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    // 1:M with Contractor Skill

    private Set<ContractorSkill> contractorSkills;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "skill")
    public Set<ContractorSkill> getContractorSkills() {
        return contractorSkills;
    }

    public void setContractorSkills(Set<ContractorSkill> contractorSkills) {

        this.contractorSkills = contractorSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill1 = (Skill) o;
        return skillId == skill1.skillId &&
                Objects.equals(skill, skill1.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, skill);
    }
}
