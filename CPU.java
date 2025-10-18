public enum CPU {
    _i5("i5"), _i7("i7");
    private final String label;
    CPU(String label){ this.label = label;}
    public String getLabel(){ return label;}
    
}
