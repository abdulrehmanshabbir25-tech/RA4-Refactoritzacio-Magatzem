/**
 * CODI REFACTORITZAT - COMMIT 2
 * Patró aplicat: Replace Magic Number with Symbolic Constant.
 * Es defineixen constants amb nom per eliminar els literals numèrics fixos.
 */
class Magatzem {
    Article[] articles;

    // PATRÓ: Replace Magic Number with Symbolic Constant
    public static final int MAX_QUALITAT = 50;
    public static final int QUALITAT_MINIMA = 0;
    public static final int LIMIT_DIES_URGENT = 11;
    public static final int LIMIT_DIES_CRITIC = 6;

    public Magatzem(Article[] articles) {
        this.articles = articles;
    }

    public void actualitzarEstat() {
        for (int i = 0; i < articles.length; i++) {
            if (!articles[i].nom.equals("Formatge Gidurat")
                    && !articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                if (articles[i].qualitat > QUALITAT_MINIMA) {
                    if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                        articles[i].qualitat = articles[i].qualitat - 1;
                    }
                }
            } else {
                if (articles[i].qualitat < MAX_QUALITAT) {
                    articles[i].qualitat = articles[i].qualitat + 1;

                    if (articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                        if (articles[i].diesPerVendre < LIMIT_DIES_URGENT) {
                            if (articles[i].qualitat < MAX_QUALITAT) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }

                        if (articles[i].diesPerVendre < LIMIT_DIES_CRITIC) {
                            if (articles[i].qualitat < MAX_QUALITAT) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }
                    }
                }
            }

            if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                articles[i].diesPerVendre = articles[i].diesPerVendre - 1;
            }

            if (articles[i].diesPerVendre < 0) {
                if (!articles[i].nom.equals("Formatge Gidurat")) {
                    if (!articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                        if (articles[i].qualitat > QUALITAT_MINIMA) {
                            if (!articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                                articles[i].qualitat = articles[i].qualitat - 1;
                            }
                        }
                    } else {
                        articles[i].qualitat = articles[i].qualitat - articles[i].qualitat;
                    }
                } else {
                    if (articles[i].qualitat < MAX_QUALITAT) {
                        articles[i].qualitat = articles[i].qualitat + 1;
                    }
                }
            }
        }
    }
}