package LabelingStudy.nctu.minuku.dao;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import LabelingStudy.nctu.minuku.model.DataRecord.AccessibilityDataRecord;
import LabelingStudy.nctu.minukucore.dao.DAO;
import LabelingStudy.nctu.minukucore.dao.DAOException;
import LabelingStudy.nctu.minukucore.user.User;

/**
 * Created by Lawrence on 2017/9/6.
 */

public class AccessibilityDataRecordDAO implements DAO<AccessibilityDataRecord> {

    public AccessibilityDataRecordDAO(){

    }

    @Override
    public void setDevice(User user, UUID uuid) {

    }

    @Override
    public void add(AccessibilityDataRecord entity) throws DAOException {

    }

    @Override
    public void delete(AccessibilityDataRecord entity) throws DAOException {

    }

    @Override
    public Future<List<AccessibilityDataRecord>> getAll() throws DAOException {
        return null;
    }

    @Override
    public Future<List<AccessibilityDataRecord>> getLast(int N) throws DAOException {
        return null;
    }

    @Override
    public void update(AccessibilityDataRecord oldEntity, AccessibilityDataRecord newEntity) throws DAOException {

    }
}
