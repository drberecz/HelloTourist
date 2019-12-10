package hu.ps.ht.servlet;

import hu.ps.ht.dto.CategoryDTO;
import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.service.CategoryService;
import hu.ps.ht.service.EventService;
import hu.ps.ht.util.EventManagerUtil;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "AdvancedSearchServlet", urlPatterns = {"/advancedsearch"})
public class AdvancedSearchServlet extends HttpServlet {

    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Inject
    EventService eventService;

    @Inject
    CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter("categoryAdvanced");

        if (category != null) {
            request.setAttribute("eventListMessage", category);
            List<EventDTO> eventDTOs = eventService.findEventsByCategory(category);

            EventManagerUtil.convertDTOsWeeklyPatternAttributesTo3CharNames(eventDTOs);

            request.setAttribute("eventList", eventDTOs);

        }

        List<CategoryDTO> categoryDTOs = categoryService.findAll();
        
        
        categoryDTOs.stream()
                .collect(Collectors.toList());
        request.setAttribute("categoryList", categoryDTOs);

        request.getRequestDispatcher("advancedsearch.jsp").forward(request, response);
    }

}
