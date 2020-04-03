# coding=utf-8

# @file testing_program.py
# @author zheng.chez
# @date 2019/5/14

import datetime
import matplotlib.pyplot as plt
import numpy
from aio.audio_device import *


SAMPLE_DURATION = 10  # 秒
print '[.] IO设备 - 连接中'
device = AudioDevice()
print '[o] IO设备 - 已连接'

# 录音
print '[o] 拾音开始 - 期望时长%d秒' % SAMPLE_DURATION, datetime.datetime.now()
data = device.record(SAMPLE_DURATION)
print '[x] 拾音结束 - 期望时长%d秒' % SAMPLE_DURATION, datetime.datetime.now()

# 读文件
# raw_file = open('resource/f_tts.raw')
# data = raw_file.read()

print '[i] 样本数据量', len(data), 'bytes'

print '[.] 样本归一化中'
amplitude = numpy.fromstring(data, numpy.int16)
print '[.] 原始样本前段', amplitude
amplitude = amplitude * 1.0 / max(abs(amplitude))
print '[.] 归一化样本前段', amplitude
print '[o] 样本归一化完成'

print '[o] 绘制样本波形'
fig = plt.figure()
chart = fig.add_subplot(111)
chart.plot(amplitude, lw=0.5)
plt.show()
print '[x] 关闭样本波形'

print '[o] 播放开始', datetime.datetime.now()
device.play(data)
print '[x] 播放结束', datetime.datetime.now()

device.close()
print '[x] IO设备 - 已断开'

frame_duration = 0.025
frame_capacity = device.get_sample_rate() * frame_duration

fft_n_dict = {}
frame_capacity_candidates = [32, 64, 128, 256, 512, 1024]
for capacity_as_key in frame_capacity_candidates:
    fft_n_dict[capacity_as_key] = abs(frame_capacity - capacity_as_key)

sorted_list = sorted(fft_n_dict.items(), key=lambda x: x[1])
frame_capacity = sorted_list[0][0]
frame_duration = frame_capacity * 1.0 / device.get_sample_rate()

overlapping_size = 1.0 * frame_capacity / 3.0
overlapping_size = int(round(overlapping_size))

print '[i] 帧时长 =', frame_duration, ', 帧容量 =', frame_capacity, ', 帧步长 =', overlapping_size

print '[o] 绘制样本频谱'
spectrum, freqs, ts, fig = plt.specgram(amplitude, NFFT=frame_capacity, Fs=device.get_sample_rate(),
                                        window=numpy.hanning(M=frame_capacity), noverlap=overlapping_size,
                                        mode='default', scale_by_freq=True, sides='default', scale='dB',
                                        xextent=None)
plt.ylabel('Frequency')
plt.xlabel('Time(s)')
plt.title('Spectrogram')
plt.show()
print '[x] 关闭样本频谱'
