package opticaltelephonecompany.otc.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "current_call")
public class CurrentCall {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long currentCallId;
    private String startTime;
    private String endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    
    @OneToMany(mappedBy = "currentCall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> calls = new ArrayList<>();

    public CurrentCall() {
    }


    public CurrentCall(Long currentCallId, String startTime, String endTime, Users user, List<Call> calls) {
        this.currentCallId = currentCallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.calls = calls;
    }

    public Long getCurrentCallId() {
        return currentCallId;
    }

    public void setCurrentCallId(Long currentCallId) {
        this.currentCallId = currentCallId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

    @Override
    public String toString() {
        return "CurrentCall [currentCallId=" + currentCallId + ", startTime=" + startTime + ", endTime=" + endTime
                + ", calls=" + calls + "]";
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


    

   
    
}
