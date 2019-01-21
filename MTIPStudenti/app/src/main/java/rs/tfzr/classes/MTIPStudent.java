package rs.tfzr.classes;

import com.google.gson.annotations.SerializedName;

public class MTIPStudent {

    @SerializedName("_idStudent")
    protected int _idStudent;
    @SerializedName("_ime")
    protected String _ime;
    @SerializedName("_prezime")
    protected String _prezime;
    @SerializedName("_index")
    protected String _index;
    @SerializedName("_smer")
    protected MTIPStudijskiProgram _smer;

    public MTIPStudent()
    {
        this._idStudent = 0;
        this._ime = "";
        this._prezime = "";
        this._index = "";
        this._smer = new MTIPStudijskiProgram();
    }

    public int get_idStudent() {
        return _idStudent;
    }

    public void set_idStudent(int _idStudent) {
        this._idStudent = _idStudent;
    }

    public String get_ime() {
        return _ime;
    }

    public void set_ime(String _ime) {
        this._ime = _ime;
    }

    public String get_prezime() {
        return _prezime;
    }

    public void set_prezime(String _prezime) {
        this._prezime = _prezime;
    }

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public MTIPStudijskiProgram get_smer() {
        return _smer;
    }

    public void set_smer(MTIPStudijskiProgram _smer) {
        this._smer = _smer;
    }
}
