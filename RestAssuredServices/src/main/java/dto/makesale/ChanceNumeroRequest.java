package dto.makesale;


public class ChanceNumeroRequest{

    public String numero;
    public int superPlenoCombinado;
    public int superPlenoDirecto;
    public int pata;
    public int una;

    public ChanceNumeroRequest(int una,String numero) {
        this.numero = numero;
        this.una = una;
    }

    public ChanceNumeroRequest(String numero, int pata) {
        this.numero = numero;
        this.pata = pata;
    }

    public ChanceNumeroRequest(String numero, int superPlenoCombinado, int superPlenoDirecto) {
        this.numero = numero;
        this.superPlenoCombinado = superPlenoCombinado;
        this.superPlenoDirecto = superPlenoDirecto;
    }

    public ChanceNumeroRequest( int pata, int una, String numero){
        this.numero = numero;
        this.una = una;
        this.pata= pata;
    }

    public int getPata() {
        return pata;
    }

    public void setPata(int pata) {
        this.pata = pata;
    }

    public int getUna() {
        return una;
    }

    public void setUna(int una) {
        this.una = una;
    }



    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getSuperPlenoCombinado() {
        return superPlenoCombinado;
    }

    public void setSuperPlenoCombinado(int superPlenoCombinado) {
        this.superPlenoCombinado = superPlenoCombinado;
    }

    public int getSuperPlenoDirecto() {
        return superPlenoDirecto;
    }

    public void setSuperPlenoDirecto(int superPlenoDirecto) {
        this.superPlenoDirecto = superPlenoDirecto;
    }
}
