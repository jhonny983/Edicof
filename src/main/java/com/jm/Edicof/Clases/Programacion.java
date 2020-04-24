/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jm.Edicof.Clases;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

// @author Henry Joe Wong Urquiza

public class Programacion {
    //El horario de la tarea
    private Scheduler horario;
    private Trigger trigger;
    private JobDetail job;
    private int hh_prog = 0;
    private int mm_prog = 0;
    
    public Programacion(int hh, int mm){
        this.hh_prog=hh;
        this.mm_prog=mm;
    }
    
    public void set_hh_mm(int hh, int mm){
        this.hh_prog=hh;
        this.mm_prog=mm;
    }

    public int getHh_prog() {
        return hh_prog;
    }

    public int getMm_prog() {
        return mm_prog;
    }
    
    public void crearProgramacion() {
        
        try {
            SchedulerFactory factoria = new StdSchedulerFactory();
            horario = factoria.getScheduler();
            horario.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Crear programacion "+ex.getMessage());
        }

    }
    
    public void refreh_Programacion(){
        try {
            System.out.println("Deneniendo...");
            horario.clear();
            horario = null;
            trigger = null;
            job = null;
        } catch (SchedulerException ex) {
            ex.printStackTrace();
            System.out.println("Detener programacion "+ex.getMessage());
        }
    
    }
    
    public void init_Tarea() {
        System.out.println("Iniciando tarea programada...");
        //.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(hh_prog, mm_prog))
        
        //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(hh_prog).repeatForever())
        try {
            if (this.horario == null ) {
                System.out.println(" 1 Horario esta vacio");
                this.crearProgramacion();
            }else{
                if (horario.isStarted()) {
                    System.out.println("Horario esta iniciado");
                }
                if (horario.isShutdown()) {
                    System.out.println("Horario esta detenido");
                    this.crearProgramacion();
                }
            }
            job = JobBuilder.newJob(TareaProgramada.class)
                    //.withIdentity("myJob", "group1")
                    .build();
            trigger = TriggerBuilder.newTrigger()
                    //.withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(hh_prog, mm_prog))
                    .build();
            this.horario.scheduleJob(job, trigger);
            System.out.println("Tarea programada diariamente Hora y Minuto: "+hh_prog+" y "+mm_prog);
        } catch (SchedulerException ex) {
            ex.printStackTrace();
            System.out.println("Iniciar tarea "+ex.getMessage());
        }
    }
    
    public void stanby_tarea(){
        try {
            horario.shutdown();
            //horario.standby();
            
        } catch (SchedulerException ex) {
            ex.printStackTrace();
            System.out.println("Stand By programacion "+ex.getMessage());
        }
    }
}