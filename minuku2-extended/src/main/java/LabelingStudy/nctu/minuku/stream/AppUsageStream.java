package LabelingStudy.nctu.minuku.stream;

import java.util.ArrayList;
import java.util.List;

import LabelingStudy.nctu.minuku.model.DataRecord.AppUsageDataRecord;
import LabelingStudy.nctu.minukucore.model.DataRecord;
import LabelingStudy.nctu.minukucore.stream.AbstractStreamFromDevice;

/**
 * Created by Jimmy on 2017/8/8.
 */

public class AppUsageStream extends AbstractStreamFromDevice<AppUsageDataRecord> {

    public AppUsageStream(int maxSize) {
        super(maxSize);
    }

    @Override
    public List<Class<? extends DataRecord>> dependsOnDataRecordType() {
        return new ArrayList<>();
    }
}
