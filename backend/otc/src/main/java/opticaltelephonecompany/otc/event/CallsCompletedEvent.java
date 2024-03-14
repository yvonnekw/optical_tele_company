package opticaltelephonecompany.otc.event;

import java.util.List;

import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CurrentCall;

public class CallsCompletedEvent {
    
   private String username;
    private List<CurrentCall> completedCalls;

    public CallsCompletedEvent() {
    }

    public CallsCompletedEvent(String username, List<CurrentCall> completedCalls) {
        this.username = username;
        this.completedCalls = completedCalls;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CurrentCall> getCompletedCalls() {
        return completedCalls;
    }

    public void setCompletedCalls(List<CurrentCall> completedCalls) {
        this.completedCalls = completedCalls;
    }

    @Override
    public String toString() {
        return "CallsCompletedEvent [username=" + username + ", completedCalls=" + completedCalls + "]";
    }
 
    
}
