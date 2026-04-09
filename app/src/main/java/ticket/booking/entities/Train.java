package ticket.booking.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class Train {
    @JsonProperty("train_id")
    private String trainId;
    @JsonProperty("train_no")
    private String trainNo;
    private List<List<Integer>> seats;
    @JsonProperty("station_times")
    private Map<String, String> stationTimes;
    private List<String> station;

    public Train(){}
    public Train(String trainId, String trainNo, List<List<Integer>> seats, Map<String,String> stationTimes, List<String> station) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.station = station;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public List<String> getStation() {
        return station;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public void setTrainId(String trainId) { this.trainId = trainId; }
    public void setTrainNo(String trainNo) { this.trainNo = trainNo; }
    public void setSeats(List<List<Integer>> seats) { this.seats = seats; }
    public void setStationTimes(Map<String, String> stationTimes) { this.stationTimes = stationTimes; }
    public void setStation(List<String> station) { this.station = station; }

    public String getTrainId() {
        return trainId;
    }
    public String getTrainInfo () {
        return String.format("Train ID: %s Train No.: %s", trainId,trainNo);
    }

}
