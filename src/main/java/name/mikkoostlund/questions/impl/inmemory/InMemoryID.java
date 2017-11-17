package name.mikkoostlund.questions.impl.inmemory;

final class InMemoryID implements name.mikkoostlund.questions.model.ID {
    private final long id;

    public InMemoryID(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || this.getClass() != anObject.getClass()) return false;

        InMemoryID that = (InMemoryID) anObject;

        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "InMemoryID{" +
                "id=" + id +
                '}';
    }
}
