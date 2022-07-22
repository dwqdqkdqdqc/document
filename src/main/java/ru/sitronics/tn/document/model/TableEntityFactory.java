package ru.sitronics.tn.document.model;

import ru.sitronics.tn.document.model.base.BaseTableEntity;

public class TableEntityFactory {
    public static BaseTableEntity getTableEntity(NciDocumentType.NciDocumentTypeEnum documentType) {
        BaseTableEntity toReturn;

        switch (documentType) {
            case SPECIFICATION -> toReturn = new SpecificationTableEntity();
            case INFORMATION_ABOUT_SUPPLIERS_CHAIN_OF_OWNERSHIP -> toReturn = new InfoSupplierChainTableEntity();
            default -> throw new IllegalArgumentException("Wrong tableEntity type: " + documentType);
        }
        return toReturn;
    }
}
