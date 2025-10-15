public enum RAM {
    _16(16), _32(32);
    private final int gb;
    RAM(int gb){ this.gb = gb; }
    public int getGb(){ return gb; }
}