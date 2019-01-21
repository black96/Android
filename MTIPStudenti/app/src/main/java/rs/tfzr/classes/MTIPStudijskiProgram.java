package rs.tfzr.classes;

import com.google.gson.annotations.SerializedName;

public class MTIPStudijskiProgram
{
    @SerializedName("_idSmer")
    protected int _idSmer;
    @SerializedName("_nazivSmera")
    protected String _nazivSmera;
    @SerializedName("_sifra")
    protected String _sifra;

    public MTIPStudijskiProgram()
    {
        this._idSmer = 0;
        this._nazivSmera = "";
        this._sifra ="";
    }

    public int get_idSmer() {
        return _idSmer;
    }

    public void set_idSmer(int _idSmer) {
        this._idSmer = _idSmer;
    }

    public String get_nazivSmera() {
        return _nazivSmera;
    }

    public void set_nazivSmera(String _nazivSmera) {
        this._nazivSmera = _nazivSmera;
    }

    public String get_sifra() {
        return _sifra;
    }

    public void set_sifra(String _sifra) {
        this._sifra = _sifra;
    }

    @Override
    public String toString() {
        return this._nazivSmera;
    }
}
