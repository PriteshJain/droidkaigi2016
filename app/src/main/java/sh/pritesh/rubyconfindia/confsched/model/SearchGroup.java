package sh.pritesh.rubyconfindia.confsched.model;

public interface SearchGroup {

    int getId();

    String getName();

    Type getType();

    enum Type {CATEGORY, PLACE, TITLE}

}
