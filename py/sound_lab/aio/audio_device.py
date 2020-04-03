# coding=utf-8

# @file audio_device.py
# @author zheng.chez
# @date 2019-05-15

from pyaudio import PyAudio, paInt16, paInt8
import numpy


class AudioDevice:

    # 私有常量。
    _SAMPLE_SIZE = 2  # Bytes
    _SAMPLE_FORMAT = paInt16

    # 设备抽象相关对象。
    _audio_component = None
    _i_stream = None
    _o_stream = None

    # 采样配置。
    _sample_rate = 8000  # Hz
    _samples_per_chunk = 1024

    def __init__(self, sample_rate=None, sample_per_chunk=None):
        if sample_rate:
            self._sample_rate = sample_rate

        if sample_per_chunk:
            self._samples_per_chunk = sample_per_chunk

        self._audio_component = PyAudio()

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.close()

    def close(self):
        if self._i_stream:
            self._i_stream.stop_stream()
            self._i_stream.close()

        if self._o_stream:
            self._o_stream.stop_stream()
            self._o_stream.close()

        self._audio_component.terminate()

    def get_sample_rate(self):
        return self._sample_rate

    def get_samples_per_chunk(self):
        return self._samples_per_chunk

    def record(self, duration):
        if not self._i_stream:
            self._i_stream = self._audio_component.open(
                    format=self._SAMPLE_FORMAT, channels=1, rate=self._sample_rate, input=True, output=False,
                    frames_per_buffer=self._samples_per_chunk)

        sample_count = self._sample_rate * duration
        return self._i_stream.read(sample_count)

    def play(self, sample_data):
        if not self._o_stream:
            self._o_stream = self._audio_component.open(
                    format=self._SAMPLE_FORMAT, channels=1, rate=self._sample_rate, input=False, output=True,
                    frames_per_buffer=self._samples_per_chunk)

        playing_data = numpy.fromstring(sample_data, numpy.int8)
        self._o_stream.write(playing_data, len(playing_data) / self._SAMPLE_SIZE)
