package footballBetting.entities.betting;

import footballBetting.entities.betting.enums.Prediction;

import javax.persistence.*;

@Entity(name = "result_prediction")
public class ResultPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Prediction prediction;

    protected ResultPrediction() {
    }

    public ResultPrediction(Prediction prediction) {
        setPrediction(prediction);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    private void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
