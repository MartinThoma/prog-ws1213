package wordprocessor;

/**
 * Text formatting information.
 *
 * Currently this is only bold and italic.
 *
 * @author  Yasmin
 * @version  1.2
 */
public class Style {
    /** True if the text is formatted in bold, false otherwise. */
    private boolean bold;

    /** True if the text is formatted in italic, false otherwise. */
    private boolean italic;

    /**
     * Creates a new Style object.
     *
     * @param bold True iff the Style shall be bold.
     * @param italic True iff the Style shall be italic.
     */
    public Style(boolean bold, boolean italic) {
        this.bold = bold;
        this.italic = italic;
    }

    /**
     * Sets the bold and italic attributes according to the given style-change command.
     *
     * @param change The style-change command
     */
    public void apply(StyleChange change) {
        switch(change) {
            case SET_BOLD:
                this.bold = true;
                break;
            case UNSET_BOLD:
                this.bold = false;
                break;
            case SET_ITALIC:
                this.italic = true;
                break;
            case UNSET_ITALIC:
                this.italic = false;
                break;
        }
    }

    /**
     * Returns true iff text of this Style is bold.
     *
     * @return True iff text of this Style is bold.
     */
    public boolean isBold() {
        return this.bold;
    }

    /**
     * Returns true iff text of this Style is italic.
     *
     * @return True iff text of this Style is italic.
     */
    public boolean isItalic() {
        return this.italic;
    }

    /**
     * Compares Styles for equality with other objects.
     *
     * Styles are equal, when they format text in the same way, e.g.
     * both are non-bold and non-italic,
     * both are bold but non-italic,
     * both are non-bold but italic, or
     * both are bold and italic.
     *
     * @param other The object to compare to.
     * @return True iff the Styles are equal.
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other != null && other instanceof Style && this != other) {
            // warum this != other?
            // wenn this == other, dann ist result doch auf jeden Fall true, oder?
            // -0.5 Punkte

            Style otherStyle = (Style) other;
            // Wusstest du, dass eclipse dir das erstellen kann?
            // Generate Code -> Generate hash() und equals
            // man muss trotzdem noch manuell drüber schauen, aber das meiste ist dann schon korrekt
            // eingetragen.
            result = this.bold == otherStyle.isBold() && this.italic == otherStyle.isItalic();
        }
        return result;
    }

    /**
     * Returns a String containing HTML opening tags for the Style.
     *
     * The String must only contain &lt;b&gt;, &lt;i&gt;, where the former is
     * used for all bold Styles and the later is used for all italic Styles.
     *
     * For testability of the method's output the bold tag must come before
     * italic one.
     *
     * @return String containing HTML start tags for the Style
     */
    public String getHTMLStartTags() {
        String result = "";
        if (this.bold) {
            result += "<b>";
        }
        if (this.italic) {
            result += "<i>";
        }
        return result;
    }

    /**
     * Returns a String containing HTML end tags for the Style.
     *
     * The String contains any combination of &lt;/b&gt; for bold and
     * &lt;/i&gt; for italic text, and no other characters.
     *
     * Ordering of the tags must be consistent with getHTMLStartTags(), so that
     * an HTML document generated with these methods has correctly nested tags.
     *
     * @return String containing HTML end tags for the Style
     */
    public String getHTMLEndTags() {
        // sehr gut, du hast auf die Reihenfolge geachtet!
        String result = "";
        if (this.italic) {
            result += "</i>";
        }
        if (this.bold) {
            result += "</b>";
        }
        return result;
    }
}
