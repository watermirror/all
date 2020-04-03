package ilop.pre.code;

import java.util.List;

/**
 * @author zheng.chez
 * @date 2019/11/07
 */
public interface IrLibraryService {

    /**
     * 检索码库中的传统类目列表。
     *
     * @param commonParams  通用参数。
     * @param offset  检索起点偏移序号。
     * @param count  本次检索期望获取的类目数量。
     * @return  返回检索结果。
     */
    GetLegacyCategoriesResp getLegacyCategories(IrLibraryCommon commonParams,
                                                Long offset,
                                                Long count);

    /**
     * 检索码表目录。
     *
     * @param commonParams  通用参数。
     * @param parentCatalogId  父目录 Id。
     * @param offset  检索起点偏移序号。
     * @param count  本次检索期望获取的类目数量。
     * @return  返回检索结果。
     */
    GetIrConfigCatalogsResp getIrConfigCatalogs(IrLibraryCommon commonParams,
                                                String parentCatalogId,
                                                Long offset,
                                                Long count);

    /**
     * 检索型号。
     *
     * @param commonParams  通用参数。
     * @param parentCatalogId  父目录 Id。
     * @param offset  检索起点偏移序号。
     * @param count  本次检索期望获取的类目数量。
     * @return  返回检索结果。
     */
    GetLegacyModelsResp getLegacyModels(IrLibraryCommon commonParams,
                                        String parentCatalogId,
                                        Long offset,
                                        Long count);

    /**
     * 获取特定型号的红外码表。
     *
     * @param commonParams  通用参数。
     * @param modelId  型号 Id。
     * @return  返回红外码表。
     */
    IrConfig getIrConfig(IrLibraryCommon commonParams, String modelId);
}
