package ilop.pre.code;

import lombok.Data;

import java.util.Map;

/**
 * @author zheng.chez
 * @date 2019/11/07
 */
@Data
public class IrConfig {

    private Double basebandFrequency;

    private Map<String, String> keys;

    private Map<String, String> status;
}
