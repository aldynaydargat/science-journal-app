package ru.mfu.cs.sciencejournalback.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mfu.cs.sciencejournalback.db.entity.ResearchPaperEntity;
import ru.mfu.cs.sciencejournalback.db.entity.projections.BriefResearchPaperProjection;

import java.util.List;

@Repository
public interface ResearchPaperRepository extends JpaRepository<ResearchPaperEntity, Long> {

    @Query(value = """
        select new ru.mfu.cs.sciencejournalback.db.entity.projections.BriefResearchPaperProjection(rp.id, rp.title, rp.authors, rp.keywords)
        from ResearchPaperEntity rp
        where lower(rp.title) like lower(concat('%', :searchQuery, '%'))
        or lower(rp.authors) like lower(concat('%', :searchQuery, '%'))
        or lower(rp.keywords) like lower(concat('%', :searchQuery, '%'))
        """)
    List<BriefResearchPaperProjection> searchBriefResearchPapers(String searchQuery);

    @Query(value = "select rp.filename " +
            "from ResearchPaperEntity rp " +
            "where rp.id = :id")
    String findFilenameById(Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = """
        update ResearchPaperEntity rp
        set rp.filename = :filename
        where rp.id = :id
        """)
    void updateFileInfo(Long id, String filename);

}
