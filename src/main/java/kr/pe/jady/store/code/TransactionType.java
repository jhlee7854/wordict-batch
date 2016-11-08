package kr.pe.jady.store.code;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
public enum TransactionType {
    P("지불"), R("환불");
    private String codeValue;

    TransactionType(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeValue() {
        return codeValue;
    }
}
