package cd.dgi.di.notifications;

import cd.dgi.di.profiles.Role;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.Map;

public interface DataClient {
    @GetExchange(
            value="/declarations",
            accept = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody List<Map<String, Object>> declarations();

    @GetExchange(
            value="/roles",
            accept = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody List<Map<String, String>> rolesAsMAp();


    @GetExchange(
            value="/roles",
            accept = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Role> roles();

    @GetExchange(
            value="/tranches-impots",
            accept = MediaType.APPLICATION_JSON_VALUE
    )
    public List<TrancheImpot> tranchesImpots();
}
