public enum DISK {
    _512(512), _1024(1024);
    private final int gb;
    DISK(int gb){ this.gb = gb; }
    public int getGb(){ return gb; }
}