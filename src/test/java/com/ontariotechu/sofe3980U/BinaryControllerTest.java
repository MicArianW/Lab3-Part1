package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    @Test
	    public void add() throws Exception {
        this.mvc.perform(post("/").param("operand1","101").param("operator","+").param("operand2","001"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "110"))
			.andExpect(model().attribute("operand1", "101"));
    }

    @Test
	    public void add2() throws Exception {
        this.mvc.perform(post("/").param("operand1","110").param("operator","+").param("operand2","1100"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "10010"))
			.andExpect(model().attribute("operand1", "110"));
    }

    @Test
	    public void add3() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    @Test
        public void multiply() throws Exception {
        this.mvc.perform(post("/").param("operand1", "10").param("operator", "*").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110"))
            .andExpect(model().attribute("operand1", "10"));
    }

    @Test
        public void and() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "&").param("operand2", "011"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1"))
            .andExpect(model().attribute("operand1", "101"));
    }

    @Test
        public void or() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1011").param("operator", "|").param("operand2", "100"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "1011"));
    }


}