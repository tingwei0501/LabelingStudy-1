package LabelingStudy.nctu.minuku.dao;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import LabelingStudy.nctu.minuku.model.DataRecord.SensorDataRecord;
import LabelingStudy.nctu.minukucore.dao.DAO;
import LabelingStudy.nctu.minukucore.dao.DAOException;
import LabelingStudy.nctu.minukucore.user.User;

/**
 * Created by Lawrence on 2017/10/4.
 */

public class SensorDataRecordDAO implements DAO<SensorDataRecord> {

    @Override
    public void setDevice(User user, UUID uuid) {

    }

    @Override
    public void add(SensorDataRecord entity) throws DAOException {

    }

    @Override
    public void delete(SensorDataRecord entity) throws DAOException {

    }

    @Override
    public Future<List<SensorDataRecord>> getAll() throws DAOException {
        return null;
    }

    @Override
    public Future<List<SensorDataRecord>> getLast(int N) throws DAOException {
        return null;
    }

    @Override
    public void update(SensorDataRecord oldEntity, SensorDataRecord newEntity) throws DAOException {

    }
}