package cwansart.reports.domain.selection;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class SelectionRepository {

    @PersistenceContext(unitName = "report-unit")
    private EntityManager entityManager;

    public Selection getSelection(Long id) {
        System.out.println(String.format("get selection by id %d", id));

        TypedQuery<Selection> query = entityManager.createQuery("select s from Selection s where s.id = :id",
            Selection.class);
        query.setParameter("id", id);

        List<Selection> selections = query.getResultList();
        if (!selections.isEmpty()) {
            System.out.println(String.format("found selection with id %d", id));
            return selections.get(0);
        } else {
            System.out.println(String.format("did not find selection with id %d", id));
            return null;
        }
    }

    public List<Selection> getAllSelections() {
        System.out.println("get all selections");

        List<Selection> selections = entityManager.createQuery("select s from Selection s", Selection.class).getResultList();

        System.out.println(String.format("found %d selections", selections.size()));

        return selections;
    }

    public Long createSelection(Selection selection) {
        System.out.println("create selection");

        entityManager.persist(selection);

        System.out.println(String.format("created selection with id %d", selection.getId()));

        return selection.getId();
    }

    public void deleteSelection(Selection selection) {
        System.out.println("delete selection");

        entityManager.remove(selection);

        System.out.println("deleted selection");
    }

    public void updateSelection(Selection selection) {
        System.out.println("update selection");

        entityManager.merge(selection);

        System.out.println("updated selection");
    }
}
