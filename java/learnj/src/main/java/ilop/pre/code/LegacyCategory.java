package ilop.pre.code;

import lombok.Data;

/**
 * @author zheng.chez
 * @date 2019/11/07
 */
@Data
public class LegacyCategory {

    private IrConfigCatalogType catalogType;

    private String catalogId;

    private String categoryKey;

    private String categoryName;
}
