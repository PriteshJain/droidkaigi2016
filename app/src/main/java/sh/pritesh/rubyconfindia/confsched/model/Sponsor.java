package sh.pritesh.rubyconfindia.confsched.model;

import java.util.ArrayList;
import java.util.List;

public class Sponsor {

    public final String imageUrl;

    public final String url;

    public Sponsor(String imageUrl, String url) {
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public static List<Sponsor> createGoldList() {
        List<Sponsor> list = new ArrayList<>(4);
        list.add(new Sponsor("http://rubyconfindia.org/assets/images/sponsors/digitalocean.png", "https://www.digitalocean.com/"));
        list.add(new Sponsor("http://rubyconfindia.org/assets/images/sponsors/gojek.png", "http://www.go-jek.com/"));
        list.add(new Sponsor("http://rubyconfindia.org/assets/images/sponsors/josh.png", "http://www.joshsoftware.com/"));
        list.add(new Sponsor("http://rubyconfindia.org/assets/images/sponsors/qwinix.png", "http://www.qwinixtech.com/"));
        return list;
    }

    public static List<Sponsor> createSilverList() {
        List<Sponsor> list = new ArrayList<>(4);
        list.add(new Sponsor("http://rubyconfindia.org/assets/images/sponsors/codemancers.png", "http://codemancers.com/"));
        list.add(new Sponsor("http://rubyconfindia.org/assets/images/sponsors/cybrilla.png", "http://cybrilla.com/"));
        list.add(new Sponsor("http://rubyconfindia.org/assets/images/sponsors/limo-logo.png", "http://limoapp.in/"));
        list.add(new Sponsor("http://www.mavenhive.in/", "http://www.mavenhive.in/"));
        return list;
    }


}
