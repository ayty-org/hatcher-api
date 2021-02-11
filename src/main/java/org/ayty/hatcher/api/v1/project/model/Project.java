package org.ayty.hatcher.api.v1.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ayty.hatcher.api.v1.project.dto.ProjectDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private String logo;

    @NotNull
    @NotEmpty
    private LocalDate startDate;

    @NotNull
    @NotEmpty
    private LocalDate endDate;

//    @ManyToMany
//    @JoinTable(
//          name = "project_coordenator",
//          joinColumns = @JoinColumn(name = "project_id"),
//          inverseJoinColumns = @JoinColumn(name = "coordenator_id")
//    )
//    private List<User> coordenators;

//    @ManyToMany(mappedBy = "competences")
//    private List<Competence> competences;

    public static Project to(ProjectDTO projectDTO) {
        return Project.builder()
                .id(projectDTO.getId())
                .name(projectDTO.getName())
                .description(projectDTO.getDescription())
                .logo(projectDTO.getLogo())
                .startDate(projectDTO.getStartDate())
                .endDate(projectDTO.getEndDate())
                .build();
    }

    public static List<Project> to(List<ProjectDTO> projectDTOList) {
        return projectDTOList.stream().map(Project::to).collect(Collectors.toList());
    }
}