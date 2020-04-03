package ilop.pre.code;

import lombok.Data;

import java.util.List;

/**
 * @author zheng.chez
 * @date 2019/11/07
 */
@Data
public class GetLegacyModelsResp {

    private Long totalCount;

    private Long offset;

    private List<LegacyModel> models;
}
