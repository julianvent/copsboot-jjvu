package mx.jjvu.copsboot.services;

import mx.jjvu.copsboot.utility.id.UserId;

import java.time.Instant;

public record CreateReportParameters(UserId reporterId, Instant dateTime, String description) {}
