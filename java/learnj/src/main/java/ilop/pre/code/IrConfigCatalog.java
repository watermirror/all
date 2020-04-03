package ilop.pre.code;

import lombok.Data;

/**
 * @author zheng.chez
 * @date 2019/11/07
 */
@Data
public class IrConfigCatalog {

    private IrConfigCatalogType catalogType;

    private String catalogId;

    private String catalogName;
}
