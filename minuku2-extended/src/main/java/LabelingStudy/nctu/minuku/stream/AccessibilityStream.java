package LabelingStudy.nctu.minuku.stream;

import java.util.ArrayList;
import java.util.List;

import LabelingStudy.nctu.minuku.model.DataRecord.AccessibilityDataRecord;
import LabelingStudy.nctu.minukucore.model.DataRecord;
import LabelingStudy.nctu.minukucore.stream.AbstractStreamFromDevice;

/**
 * Created by Lawrence on 2017/9/6.
 */

public class AccessibilityStream extends AbstractStreamFromDevice<AccessibilityDataRecord> {
    public AccessibilityStream(int maxSize) {
        super(maxSize);
    }

    @Override
    public List<Class<? extends DataRecord>> dependsOnDataRecordType() {
        return new ArrayList<>();
    }
}
