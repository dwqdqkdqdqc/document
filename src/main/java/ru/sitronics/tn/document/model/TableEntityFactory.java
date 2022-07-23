package ru.sitronics.tn.document.model;

import ru.sitronics.tn.document.model.base.BaseTableEntity;

public class TableEntityFactory {
    public static BaseTableEntity getTableEntity(NciDocumentType.NciDocumentTypeEnum documentType) {
        BaseTableEntity toReturn;

        switch (documentType) {
            case SPECIFICATION -> toReturn = new SpecificationTableEntity();
            case INFORMATION_ABOUT_SUPPLIERS_CHAIN_OF_OWNERSHIP -> toReturn = new InfoSupplierChainTableEntity();
            case PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR ->
                    toReturn = new ProgressOfProductionForShipmentOfMtrTableEntity();
            default -> throw new IllegalArgumentException("Wrong tableEntity type: " + documentType);
        }
        return toReturn;
    }
}
