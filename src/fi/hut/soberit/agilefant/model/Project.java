package fi.hut.soberit.agilefant.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;

import flexjson.JSON;

/**
 * A Hibernate entity bean which represents a project.
 * <p>
 * Conceptually, a project is a type of a backlog. A project-backlog represents
 * work (iterations, backlog items, todos) to be done towards some project
 * outcome (documents, code, plans, etc.).
 * <p>
 * A project is further divided up to smaller containers for work, the
 * iterations. Project also is a part of a bigger container, the product. Since
 * a project is a backlog, it can contain backlog items, which, in turn, are
 * smaller containers for work.
 * <p>
 * Example projects would be "Acme KillerApp v1.3" or "User Documentation".
 * <p>
 * A project is part of a product. It can contain iterations. It has an optional
 * starting and ending dates, as well as an owner. A project is also bound to
 * some activity type. It also carries information on effort estimations. A
 * project has a rank number, which corresponds to its priority. The rank number
 * doesn't describe the project's absolute rank order; the number must be
 * compared to all other project's ranks to find out rank order.
 * 
 * @see fi.hut.soberit.agilefant.model.Backlog
 * @see fi.hut.soberit.agilefant.model.BacklogItem
 * @see fi.hut.soberit.agilefant.model.ProjectType
 * @see fi.hut.soberit.agilefant.model.Iteration
 */
@Entity
@BatchSize(size = 20)
public class Project extends Backlog {

    private Product product;

    private ProjectType projectType;

    private Date endDate;

    private Date startDate;

//    private List<Iteration> iterations = new ArrayList<Iteration>();

    private List<Story> stories = new ArrayList<Story>();

    private int rank = 0;

    private Status status = Status.GREEN;

    /** The product, under which this project belongs. */
    @ManyToOne(optional = false)
    // @JoinColumn (nullable = true)
    @JSON(include = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /** Iterations under this project. */
//    @OneToMany(mappedBy = "parent")
//    @BatchSize(size = 20)
//    @JSON(include = false)
//    public List<Iteration> getIterations() {
//        return iterations;
//    }
//
//    public void setIterations(List<Iteration> iterations) {
//        this.iterations = iterations;
//    }

    @JSON
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JSON
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    @JSON(include = false)
    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    @Column(nullable = false)
    @JSON
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Returns the status of the project.
     * 
     * @return the status of the project.
     */
    @Enumerated(EnumType.ORDINAL)
    @JSON
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    @OneToMany(mappedBy = "project")
    public List<Story> getStories() {
        return stories;
    }

}