package id.or.tauhid.doadandzikir;

public class ModelDoa {

    public static final int BACA_DOA = 0;

    private String mName;
    private String latinDoa;
    private String artiDoa;
    private String sumberDoa;
    private String bName;
    private int mType;

    private int read;

    public int getReadCount() {
        return this.read;
    }

    public void setReadCount(int read) {
        this.read = read;
    }


    public ModelDoa(String name, String latin, String arti, String sumber, String butong, int type) {
        this.mName = name;
        this.latinDoa = latin;
        this.artiDoa = arti;
        this.sumberDoa = sumber;
        this.bName = butong;
        this.mType = type;

        String[] data = butong.split("\\s");
        if (data.length > 0) {
            String count = data[1].substring(0, data[1].length() - 1);
            read = Integer.parseInt(count);
        }
        //---------------
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        this.mName = name;
    }

    public String getLatin() {
        return latinDoa;
    }
    public void setLatin(String latin) {
        this.latinDoa = latin;
    }

    public String getSumber() {
        return sumberDoa;
    }
    public void setSumber(String sumber) {
        this.sumberDoa = sumber;
    }

    public String getArti() {
        return artiDoa;
    }
    public void setArti(String arti) {
        this.artiDoa = arti;
    }

    public String ambilName() {
        return bName;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) { this.mType = type; }


    public void setNama(String butonk) {
        this.bName = butonk;
    }

}
