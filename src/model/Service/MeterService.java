package model.Service;

public interface MeterService {
    int updateGasReading(String id);
    int updateElecReading(String id);
    int generate_history(String id);
    int update_month(String id);
}
