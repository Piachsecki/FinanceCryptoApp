package com.piachsecki.financecryptoapp.service;

import com.piachsecki.financecryptoapp.domain.MyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@RequiredArgsConstructor
public class RequestQueueService {
    private final BlockingQueue<MyRequest> requestQueue = new LinkedBlockingQueue<>();

    public void addRequest(MyRequest request) {
        requestQueue.offer(request);
    }

    public MyRequest pollRequest() {
        return requestQueue.poll();
    }

}
