package dto.consultarcategoriafiltro.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CategoriasConvenios {

    @JsonProperty("7")
    public ArrayList<_7> _7;

    public ArrayList<dto.consultarcategoriafiltro.response._7> get_7() {
        return _7;
    }

    public void set_7(ArrayList<dto.consultarcategoriafiltro.response._7> _7) {
        this._7 = _7;
    }
}
