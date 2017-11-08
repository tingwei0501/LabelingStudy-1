package LabelingStudy.nctu.minuku.stream;

import java.util.ArrayList;
import java.util.List;

import LabelingStudy.nctu.minuku.model.DataRecord.ActivityRecognitionDataRecord;
import LabelingStudy.nctu.minukucore.model.DataRecord;
import LabelingStudy.nctu.minukucore.stream.AbstractStreamFromDevice;

/**
 * Created by Lawrence on 2017/5/22.
 */

public class ActivityRecognitionStream extends AbstractStreamFromDevice<ActivityRecognitionDataRecord> {

    public ActivityRecognitionStream(int maxSize) {
        super(maxSize);
    }

    @Override
    public List<Class<? extends DataRecord>> dependsOnDataRecordType() {
        return new ArrayList<>();
    }

}
