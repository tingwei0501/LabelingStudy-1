package LabelingStudy.nctu.minuku.stream;

import java.util.ArrayList;
import java.util.List;

import LabelingStudy.nctu.minuku.model.DataRecord.RingerDataRecord;
import LabelingStudy.nctu.minukucore.model.DataRecord;
import LabelingStudy.nctu.minukucore.stream.AbstractStreamFromDevice;

/**
 * Created by Lawrence on 2017/8/22.
 */

public class RingerStream extends AbstractStreamFromDevice<RingerDataRecord> {
    public RingerStream(int maxSize) {
        super(maxSize);
    }

    @Override
    public List<Class<? extends DataRecord>> dependsOnDataRecordType() {
        return new ArrayList<>();
    }
}
