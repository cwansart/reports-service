package cwansart.reports.domain.selection;

public class SelectionBuilder {

    private Selection selection;

    private SelectionBuilder() {
        selection = new Selection();
    }

    public static SelectionBuilder newBuilder() {
        return new SelectionBuilder();
    }

    public SelectionBuilder withId(Long id) {
        selection.setId(id);
        return this;
    }

    public SelectionBuilder withTitle(String title) {
        selection.setTitle(title);
        return this;
    }

    public SelectionBuilder withDescription(String description) {
        selection.setDescription(description);
        return this;
    }

    public Selection build() {
        return selection;
    }
}
