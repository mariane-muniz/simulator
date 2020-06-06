package com.sascar.simulator.controllers;

import java.util.Objects;
import java.util.Optional;

import com.sascar.simulator.dtos.BreadCrumbData;
import com.sascar.simulator.dtos.LinkData;
import com.sascar.simulator.entities.Simulation;
import com.sascar.simulator.facades.SimulationFacade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/simulator")
@RequiredArgsConstructor
public class SimulatorPageController {
    private final SimulationFacade simulationFacade;

    @GetMapping
    public String redirect() {
        return "/simulator/details";
    }

    @GetMapping("/detail/{id}")
    public String detail(
        final Model model,
        @Validated @PathVariable(value = "id") long id) {
        Optional<Simulation> simulation = this.simulationFacade.getSimulation(id);
        if (simulation.isPresent())
            model.addAttribute("simulation", simulation.get());
        return "/simulator/detail";
    }

    @GetMapping("/list/remove/{id}")
    public String delete(@Validated @PathVariable(value = "id") long id) {
        log.info("Delete " + id);
        this.simulationFacade.delete(id);
        return "redirect:/simulator/list";
    }

    @PostMapping({ "/list/add", "/list/edit/{id}" })
    public String receiveForm(@Validated Simulation simulation, final Model model) {
        simulation = this.simulationFacade.save(simulation);
        return "redirect:/simulator/detail/" + simulation.getId();
    }

    @GetMapping({ "/list/add", "/list/edit/{id}" })
    public String getForm(@PathVariable(required = false) Long id, final Model model) {
        final BreadCrumbData breadCrumb = new BreadCrumbData()
            .add(new LinkData("", "Simulator"))
            .add(new LinkData("/simulator/list", "List"))
            .add(new LinkData("/simulator/list/add", "Add"));
        model.addAttribute("breadCrumb", breadCrumb);
        model.addAttribute("action", "Add");
        if (Objects.nonNull(id)) {
            final Optional<Simulation> simulation = 
                this.simulationFacade.getSimulation(id);
            if (simulation.isPresent()) {
                model.addAttribute("simulation", simulation.get());
            }
        }
        return "simulator/form";
    }

    @GetMapping("/list")
    public String getAll(final Model model) {
        final Iterable<Simulation> simulations = this.simulationFacade.getSimulations();
        final BreadCrumbData breadCrumb = new BreadCrumbData()
            .add(new LinkData("", "Simulator"))
            .add(new LinkData("/simulator/list", "List"));
        model.addAttribute("simulations", simulations);
        model.addAttribute("breadCrumb", breadCrumb);
        model.addAttribute("action", "Add");
        return "simulator/list";
    }
}