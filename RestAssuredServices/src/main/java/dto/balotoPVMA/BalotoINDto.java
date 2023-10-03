package dto.balotoPVMA;

import java.util.ArrayList;

public class BalotoINDto {

    public int duration;
    public String gameName;
    public int price;
    public ArrayList<Board> boards;
    public String serie1;
    public String serie2;
    public boolean provocarTimeout;
    public String codigoVendedor;
    public int valor;
    public String canalId;
    public String codigoTerminal;
    public String tipoUsuario;
    public int secuenciaTransaccion;
    public long transactionTime;
    public String seriePrefijo;
    public String serieActual;



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<Board> boards) {
        this.boards = boards;
    }

    public String getSerie1() {
        return serie1;
    }

    public void setSerie1(String serie1) {
        this.serie1 = serie1;
    }

    public String getSerie2() {
        return serie2;
    }

    public void setSerie2(String serie2) {
        this.serie2 = serie2;
    }

    public boolean isProvocarTimeout() {
        return provocarTimeout;
    }

    public void setProvocarTimeout(boolean provocarTimeout) {
        this.provocarTimeout = provocarTimeout;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getCanalId() {
        return canalId;
    }

    public void setCanalId(String canalId) {
        this.canalId = canalId;
    }

    public String getCodigoTerminal() {
        return codigoTerminal;
    }

    public void setCodigoTerminal(String codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getSecuenciaTransaccion() {
        return secuenciaTransaccion;
    }

    public void setSecuenciaTransaccion(int secuenciaTransaccion) {
        this.secuenciaTransaccion = secuenciaTransaccion;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getSeriePrefijo() {
        return seriePrefijo;
    }

    public void setSeriePrefijo(String seriePrefijo) {
        this.seriePrefijo = seriePrefijo;
    }

    public String getSerieActual() {
        return serieActual;
    }

    public void setSerieActual(String serieActual) {
        this.serieActual = serieActual;
    }
}
